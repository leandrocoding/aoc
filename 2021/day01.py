import os

path = os.path.join(os.path.dirname(__file__), 'day01.txt')

with open(path, "r") as f:
    inputdataraw = f.readlines()
    inputdata = [int(imp) for imp in inputdataraw]

# Part 1

counterp1 = 0
prevline = inputdata[0]

for line in inputdata:
    curline = line
    if prevline < curline:
        counterp1+=1
    prevline = curline

# Part 2

counterp2 = 0
prevval = inputdata[0] + inputdata[1] + inputdata[2]

for i in range(len(inputdata) -2):
    curval = inputdata[i] + inputdata[i+1] + inputdata[i+2]
    if prevval < curval:
        counterp2 +=1
    prevval = curval
    
print(f"AOC 2021 Day 01 Part 1: {counterp1}")
print(f"AOC 2021 Day 01 Part 2: {counterp2}")


