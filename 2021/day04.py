import os
path = os.path.join(os.path.dirname(__file__), 'day04.txt')

with open(path) as f:
    inputdata = f.readlines()

inData = [[[int(n) for n in x.rstrip().split()] for x in inputdata[2+i*6:7+i*6]] for i in range(100)]

def checkValid(grid):
    # Check Rows
    for row in grid:
        if sum(row) == -5:
            return True
    
    # Check Colls:
    for i in range(len(grid)):
        cursum = 0
        for j in range(len(grid)):
            cursum += grid[j][i]
        if cursum == -5:
            return True
    
    return False

def checkAll(curFields, curlist):

    for gridN in range(len(curFields)):
        if checkValid(curFields[gridN]) and gridN not in curlist:
            curlist.append(gridN)

    return curlist


def replaceallOcs(tobemodi, toReplaceN):
    tobemodi = [[[val if val!= toReplaceN else -1 for val in row] for row in grid] for grid in tobemodi]
    return tobemodi

def main(cur):
    toreplist = [int(x) for x in inputdata[0].rstrip().split(",")]
    i = 0
    res = []
    while True:
        
        cur = replaceallOcs(cur, toreplist[i])
        res = checkAll(cur, res)
        if res!= []:
            if len(res) == 1:
                latestnum = toreplist[i]
                gridsum = sum([val if val!=-1 else 0 for row in cur[res[0]] for val in row])
            
                print(f"Part 1: {latestnum*gridsum}")
            
            if len(res) == 100:
                latestnum = toreplist[i]
                gridsum = sum([val if val!=-1 else 0 for row in cur[res[-1]] for val in row])
                print(f"Part 2: {latestnum*gridsum}")
                break
        i+= 1

main(list(inData))
