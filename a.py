#!/usr/local/bin/python
import os
rootdir="./kg"
for parent,dirnames,filenames in os.walk(rootdir):
    for filename in filenames:
        print filename
        os.system('java -cp bin/ kanga.kgi < ./kg/'+filename+' > res2')
        os.system('java -jar kgi.jar < ./kg/'+filename+'> res1')
        os.system('diff res1 res2')
