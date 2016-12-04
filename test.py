#!/usr/local/bin/python
import os
rootdir="./kg"
for parent,dirnames,filenames in os.walk(rootdir):
    for filename in filenames:
        if(filename.startswith('.')):
            continue
        print filename
        os.system('java -cp bin/ kanga.kgi < ./mykg/'+filename+' > res2')
        os.system('java -jar kgi.jar < ./kg/'+filename+'> res1')
        os.system('diff res1 res2')
        os.system('rm res1 res2')
