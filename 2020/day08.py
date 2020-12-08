import os
from copy import deepcopy
path = os.path.join(os.path.dirname(__file__), 'day08.txt')

with open(path) as f:
    inputdata = f.readlines()

instructions = []
for line in inputdata:
    words = line.rstrip().split()
    opp = words[0]
    vall = int(words[1])
    instructions.append([opp, vall, False])

def run(innerint):
    i = 0
    acc = 0
    while i<len(innerint) and not innerint[i][2]:
        innerint[i][2] = True
        if innerint[i][0] == "acc":
            acc += innerint[i][1]
            i += 1
        elif innerint[i][0] == "jmp":
            i += innerint[i][1]
        elif innerint[i][0] == "nop":
            i += 1
        else:
            print(f"Error at i: {i} Unknown OP: {innerint[i][0]}")
        
    if i==len(innerint):
        print(acc)
        return acc
    else:
        return 0
    

def mainpt2(instlist):
    for j in range(len(instlist)):

        newinst = deepcopy(instlist)
        
        if newinst[j][0] == "jmp":
            newinst[j][0] = "nop"
        elif newinst[j][0] == "nop":
            newinst[j][0] = "jmp"
        else:
            continue
        acc = run(newinst)
        if acc:
            return acc

        

mainpt2(instructions)






