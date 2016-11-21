/*     */ package util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Hashtable;
/*     */ import syntaxtree.Stmt;
/*     */ 
/*     */ public class KangaRuntime
/*     */ {
/*   9 */   public static final String[] registerName = { "a0", "a1", "a2", "a3", "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "t8", "t9", "v0", "v1" };
/*     */   
/*     */ 
/*     */   public static final String dumpCurrentStack = "dumpCurrentStack";
/*     */   
/*     */ 
/*     */   public static final String dumpStack = "dumpStack";
/*     */   
/*     */ 
/*     */   public static final String dumpRegisters = "dumpRegisters";
/*     */   
/*     */ 
/*  21 */   Hashtable<Stmt, String> stmtInfo = new Hashtable();
/*     */   
/*  23 */   public KangaRuntime(Hashtable<Stmt, String> paramHashtable) { this.stmtInfo = paramHashtable;
/*  24 */     for (int i = 0; i < registerName.length; i++) {
/*  25 */       this.registerHash[i] = new memory.Reg(registerName[i], null);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/*  30 */     return "stmtList:" + this.stmtList + "\n" + "labelHash:" + this.labelHash + "\n" + "procedureHash:" + this.procedureHash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String registerToString()
/*     */   {
/*  38 */     StringBuffer localStringBuffer = new StringBuffer();
/*  39 */     for (int i = 0; i < this.registerHash.length; i++) {
/*  40 */       if (!this.registerHash[i].isNull())
/*  41 */         localStringBuffer.append(">" + registerName[i] + ":" + this.registerHash[i] + "\n");
/*     */     }
/*  43 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*  46 */   public String stackToString() { StringBuffer localStringBuffer = new StringBuffer();
/*  47 */     localStringBuffer.append(">Current stack (" + this.stackStack.size() + ")\n");
/*  48 */     localStringBuffer.append(this.stackStack.peek());
/*  49 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*  52 */   public memory.Reg getReg(int paramInt) { return this.registerHash[paramInt]; }
/*     */   
/*  54 */   public memory.Int getInt(String paramString) { if (this.integerHash.get(paramString) == null) this.integerHash.put(paramString, new memory.Int(paramString));
/*  55 */     return (memory.Int)this.integerHash.get(paramString);
/*     */   }
/*     */   
/*  58 */   public memory.Label getLabel(String paramString) throws memory.KangaLabelException { memory.Label localLabel = (memory.Label)this.labelHash.get(paramString);
/*  59 */     if (localLabel == null) {
/*  60 */       System.err.println("Label " + paramString + " doesn't exist.");
/*  61 */       throw new memory.KangaLabelException();
/*     */     }
/*  63 */     return localLabel;
/*     */   }
/*     */   
/*     */ 
/*  67 */   public java.util.Vector<Stmt> stmtList = new java.util.Vector();
/*  68 */   public java.util.HashMap<String, memory.Label> labelHash = new java.util.HashMap();
/*  69 */   public java.util.HashMap<String, int[]> procedureHash = new java.util.HashMap();
/*     */   
/*     */ 
/*  72 */   protected java.util.Stack<memory.Stack> stackStack = new java.util.Stack();
/*  73 */   public visitor.Interp ivisitor = new visitor.Interp(this);
/*  74 */   public memory.Reg[] registerHash = new memory.Reg[registerName.length];
/*  75 */   public Hashtable<String, memory.Int> integerHash = new Hashtable();
/*     */   
/*     */ 
/*  78 */   public int pc = 0;
/*  79 */   public boolean exit = false;
/*  80 */   public boolean exception = false;
/*     */   public int aload; public int astore;
/*  82 */   public void initializeStack(memory.Stack paramStack) { this.stackStack.push(paramStack); }
/*  83 */   public memory.Stack getCurrentStack() { return (memory.Stack)this.stackStack.peek(); }
/*     */   
/*     */   public int hload;
/*     */   public int hstore;
/*     */   public int hallocate;
/*  88 */   public memory.Stack pushStack(int paramInt1, int paramInt2, int paramInt3) throws memory.KangaException { if (this.stackStack.size() > 50) {
/*  89 */       System.err.println("Runtime stack size is set to 50, consider increasing it...");
/*     */       
/*     */ 
/*  92 */       this.exception = true;
/*  93 */       throw new memory.KangaException();
/*     */     }
/*  95 */     memory.Stack localStack1 = (memory.Stack)this.stackStack.peek();
/*     */     memory.Stack localStack2;
/*     */     try {
/*  98 */       localStack2 = new memory.Stack(paramInt1, paramInt2, paramInt3, this.pc + 1, false);
/*     */     }
/*     */     catch (memory.KangaException localKangaException) {
/* 101 */       this.exception = true;
/* 102 */       throw localKangaException;
/*     */     }
/* 104 */     localStack1.copyCallArgToStack(localStack2);
/* 105 */     this.stackStack.push(localStack2);
/* 106 */     return localStack2; }
/*     */   
/*     */   public int move;
/* 109 */   public memory.Stack popStack() { if (this.stackStack.size() <= 1) {
/* 110 */       System.err.println("Stack size is 1, cannot pop anymore");
/* 111 */       this.exception = true;
/* 112 */       return null;
/*     */     }
/* 114 */     memory.Stack localStack1 = (memory.Stack)this.stackStack.pop();
/* 115 */     memory.Stack localStack2 = (memory.Stack)this.stackStack.peek();
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 120 */       localStack1.copyStackToCallArg(localStack2);
/*     */     } catch (memory.KangaException localKangaException) {
/* 122 */       this.exception = true;
/*     */     }
/* 124 */     localStack2.resetCallArgs();
/* 125 */     this.pc = localStack1.getReturnPC();
/* 126 */     return localStack1;
/*     */   }
/*     */   
/*     */   public int call;
/*     */   public int binop;
/* 131 */   int count = 0;
/*     */   
/* 133 */   public void run() { Stmt localStmt = null;
/* 134 */     while ((this.pc >= 0) && (!this.exit) && (!this.exception)) {
/* 135 */       localStmt = (Stmt)this.stmtList.elementAt(this.pc);
/* 136 */       if (localStmt == null) {
/* 137 */         if (this.stackStack.size() == 1)
/*     */         {
/*     */ 
/* 140 */           this.exit = true;
/*     */         }
/*     */         else {
/* 143 */           popStack();
/*     */         }
/*     */       } else {
/* 146 */         if (Environment.instructionOutput) {
/* 147 */           System.out.println(">" + this.pc + " " + (String)this.stmtInfo.get(localStmt));
/*     */         }
/* 149 */         localStmt.accept(this.ivisitor);
/* 150 */         if (this.count++ > Environment.maxRuntimeInstruction) {
/* 151 */           System.err.println("Exceeded maximum number of instructions allowed (" + Environment.maxRuntimeInstruction + "), exiting...");
/* 152 */           this.exit = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 157 */     if (this.exception) {
/* 158 */       System.err.println("Please check near the following source code: ");
/* 159 */       System.err.println((String)this.stmtInfo.get(localStmt));
/*     */     }
/*     */     
/* 162 */     if (Environment.instructionCount) {
/* 163 */       System.out.println("> Instructions:" + this.count + " aload:" + this.aload + " astore:" + this.astore + " hload:" + this.hstore + " hallocate:" + this.hallocate + " move:" + this.move + " call:" + this.call + " binop:" + this.binop);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/util/KangaRuntime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */