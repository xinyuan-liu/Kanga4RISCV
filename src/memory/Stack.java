/*     */ package memory;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class Stack
/*     */ {
/*     */   public int paramSize;
/*     */   public Memory[] stack;
/*     */   Memory[] callArg;
/*     */   boolean warning;
/*  11 */   int returnPC = 64537;
/*     */   
/*  13 */   int maxPASSARGset = 0;
/*  14 */   int maxPASSARGgiven = 0;
/*     */   
/*     */   public Stack(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) throws KangaStackException
/*     */   {
/*  18 */     if ((paramInt2 < 0) || (paramInt2 >= 1000000)) {
/*  19 */       System.err.println("Stack size must be between 0-999999(" + paramInt2 + ")");
/*  20 */       throw new KangaStackException();
/*     */     }
/*  22 */     this.paramSize = paramInt1;
/*  23 */     this.stack = new Memory[paramInt2];
/*  24 */     this.callArg = new Memory[paramInt3];
/*  25 */     this.warning = paramBoolean;
/*  26 */     this.returnPC = paramInt4;
/*     */   }
/*     */   
/*  29 */   public int getReturnPC() { return this.returnPC; }
/*     */   
/*     */   public void setCallArg(int paramInt, Memory paramMemory) throws KangaStackException {
/*  32 */     if ((paramInt < 0) || (paramInt >= this.callArg.length)) {
/*  33 */       System.err.println("Set call arg index is out of bound (" + paramInt + ")" + " relative to the max param size");
/*     */       
/*  35 */       throw new KangaStackException();
/*     */     }
/*  37 */     this.callArg[paramInt] = paramMemory;
/*  38 */     if (paramInt + 1 > this.maxPASSARGset) this.maxPASSARGset = (paramInt + 1);
/*     */   }
/*     */   
/*  41 */   public Memory getCallArg(int paramInt) throws KangaStackException { if ((paramInt < 0) || (paramInt >= this.callArg.length)) {
/*  42 */       System.err.println("Get arg index is out of bound (" + paramInt + ")" + " relative to the max param size");
/*     */       
/*  44 */       throw new KangaStackException();
/*     */     }
/*  46 */     Memory localMemory = this.callArg[paramInt];
/*  47 */     if (localMemory == null) {
/*  48 */       System.err.println("Reading call arg location " + paramInt + " which is uninitialized.");
/*  49 */       throw new KangaStackException();
/*     */     }
/*  51 */     return localMemory;
/*     */   }
/*     */   
/*  54 */   public void resetCallArgs() { for (int i = 0; i < this.callArg.length; i++) this.callArg[i] = null;
/*  55 */     this.maxPASSARGset = 0;
/*     */   }
/*     */   
/*  58 */   public void copyCallArgToStack(Stack paramStack) throws KangaException { if (this.maxPASSARGset > paramStack.paramSize) {
/*  59 */       System.err.println("Cannot copy a bigger call arg size " + this.maxPASSARGset + " to " + paramStack.paramSize);
/*  60 */       throw new KangaStackException();
/*     */     }
/*  62 */     if (this.maxPASSARGset > paramStack.stack.length) {
/*  63 */       System.err.println("Arg size " + this.maxPASSARGset + " is greater than stack size " + paramStack.stack.length);
/*  64 */       throw new KangaStackException();
/*     */     }
/*     */     try {
/*  67 */       for (int i = 0; i < this.maxPASSARGset; i++)
/*  68 */         paramStack.stack[i] = this.callArg[i];
/*     */     } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/*  70 */       System.err.println("Fatal interpreter error: " + localArrayIndexOutOfBoundsException);
/*  71 */       throw new KangaStackException();
/*     */     }
/*  73 */     paramStack.maxPASSARGgiven = this.maxPASSARGset;
/*     */   }
/*     */   
/*  76 */   public void copyStackToCallArg(Stack paramStack) throws KangaException { if (this.maxPASSARGgiven > paramStack.maxPASSARGset) {
/*  77 */       System.err.println("Cannot copy stack's arg size " + this.maxPASSARGgiven + " to " + paramStack.maxPASSARGset);
/*  78 */       throw new KangaStackException();
/*     */     }
/*  80 */     if ((paramStack.callArg.length < this.maxPASSARGgiven) || (this.stack.length < this.maxPASSARGgiven))
/*     */     {
/*  82 */       System.err.println("ERROR, copying array size " + this.maxPASSARGgiven + " to " + this.stack.length + "&" + paramStack.callArg.length);
/*     */       
/*     */ 
/*     */ 
/*  86 */       throw new KangaStackException();
/*     */     }
/*     */     try {
/*  89 */       for (int i = 0; i < this.maxPASSARGgiven; i++)
/*  90 */         paramStack.callArg[i] = this.stack[i];
/*     */     } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/*  92 */       System.err.println("Fatal interpreter error: " + localArrayIndexOutOfBoundsException);
/*  93 */       throw new KangaStackException();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setStack(int paramInt, Memory paramMemory) throws KangaStackException {
/*  98 */     if ((paramInt < 0) || (paramInt >= 1000000)) {
/*  99 */       System.err.println("Stack size must be between 0-999999.");
/* 100 */       throw new KangaStackException();
/*     */     }
/* 102 */     if ((this.warning) && (paramInt < this.maxPASSARGgiven)) {
/* 103 */       System.err.println("Writing stack at " + paramInt + " even though it is reserved for argument.");
/*     */     }
/*     */     try {
/* 106 */       this.stack[paramInt] = paramMemory;
/*     */     } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 108 */       System.err.println("Stack length=" + this.stack.length + " but accessing " + paramInt);
/*     */       
/* 110 */       throw new KangaStackException();
/*     */     }
/*     */   }
/*     */   
/* 114 */   public Memory getStack(int paramInt) throws KangaStackException { if ((paramInt < 0) || (paramInt >= 1000000)) {
/* 115 */       System.err.println("Stack size must be between 0-999999.");
/* 116 */       throw new KangaStackException();
/*     */     }
/*     */     Memory localMemory;
/*     */     try {
/* 120 */       localMemory = this.stack[paramInt];
/*     */     } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 122 */       System.err.println("Stack length=" + this.stack.length + " but accessing " + paramInt);
/*     */       
/* 124 */       throw new KangaStackException();
/*     */     }
/* 126 */     return localMemory;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 131 */     StringBuffer localStringBuffer = new StringBuffer();
/* 132 */     localStringBuffer.append(">Stack's static param size:" + this.paramSize + ", max stack size:" + this.stack.length + ", max call arg size:" + this.callArg.length + "\n");
/*     */     
/*     */ 
/*     */ 
/* 136 */     localStringBuffer.append(">  max PASSARG used:" + this.maxPASSARGset + ", max PASSARG given:" + this.maxPASSARGgiven + "\n");
/*     */     
/* 138 */     for (int i = 0; i < this.stack.length; i++) {
/* 139 */       Memory.resetPrintHistory();
/* 140 */       localStringBuffer.append(">  stack(" + i + "): " + this.stack[i] + "\n");
/*     */     }
/* 142 */     for (int i = 0; i < this.callArg.length; i++) {
/* 143 */       Memory.resetPrintHistory();
/* 144 */       if (this.callArg[i] != null)
/* 145 */         localStringBuffer.append(">  PASSARG(" + i + "): " + this.callArg[i] + "\n");
/*     */     }
/* 147 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /Users/liuxinyuan/Desktop/kgi.jar!/memory/Stack.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */