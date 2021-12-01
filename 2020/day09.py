import os
import itertools
path = os.path.join(os.path.dirname(__file__), 'day09.txt')

with open(path, "r") as f:
    inputdata = f.readlines()

numberlist = list(map(lambda x: int(x.rstrip()), inputdata))
# Part 1
for i in range(25, len(numberlist)): 
    currentnum = numberlist[i]
    sumofnums = itertools.combinations(numberlist[i-25:i],2)
    for summ in sumofnums:
        if sum(summ)==currentnum:
            break
    else:
        result = currentnum
        print(result)
        break

# Part 2

head = 0
tail = 0
while head<=len(numberlist):
    curlist = numberlist[tail:head]

    if sum(curlist)==result:
        print(min(curlist)+max(curlist))
        break
    elif sum(curlist)<result:
        head+=1
    elif sum(curlist)>result:
        tail+=1
