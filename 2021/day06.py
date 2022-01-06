import os
path = os.path.join(os.path.dirname(__file__), 'day06.txt')

with open(path) as f:
    inputdata = f.read()

StartingFish = [int(age) for age in inputdata.split(",")]

nStorage = []

for i in range(9):
    nStorage.append(StartingFish.count(i))

def aDayhasPassed(localNStorage):
    newnCount = [0 for i in range(len(localNStorage))]
    newnCount[8] = localNStorage[0]
    newnCount[6] = localNStorage[0]
    for nCount in range(1,len(localNStorage)):
        newnCount[nCount-1] += localNStorage[nCount]
    
    return newnCount

def runxDays(days):
    localNStorage = list(nStorage)
    for i in range(days):
        localNStorage = aDayhasPassed(localNStorage)
    return sum(localNStorage)

if __name__ == '__main__':
    print(f"Part 1: {runxDays(80)}")
    print(f"Part 2: {runxDays(256)}")