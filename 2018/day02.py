import os
from difflib import SequenceMatcher

def similar(a, b):
    return SequenceMatcher(None, a, b).ratio()
path = os.path.join(os.path.dirname(__file__), 'day02.txt')

with open(path) as f:
    inputdata = f.readlines()

def checkline(line):
    two = False
    three = False
    for letter in line:
        letcount = line.count(letter)
        # print(letter)
        # print(letcount)
        if letcount == 2:
            two = True
        elif letcount == 3:
            three = True
        if two and three:
            return (1,1)
    if two:
        return (1,0)

    if three:
        return (0,1)
    return (0,0)

totaltwo = 0
totalthree = 0
for line in inputdata:
    curtwo, curthree = checkline(line)
    totaltwo += curtwo
    totalthree += curthree

def part2():

    for i in range(len(inputdata)):
        for j in range(i+1,len(inputdata)):
            sim = similar(inputdata[i],inputdata[j])
            if sim >0.95:
                # print(f"String 1: {inputdata[i]}\n String 2: {inputdata[j]}")
                return f"String 1: {inputdata[i]}String 2: {inputdata[j]}"
        



print(similar("ohwmwtcxjqnzhgkdylftpviusr","pnwmwtcxjqnzhgkdylftpviusr"))
print("\nAOC 2018 Day 02:\n")
print(f"Part 1: \nTwo: {totaltwo}\nThree: {totalthree} \nResult: {totaltwo*totalthree}")
print(f"\nPart 2: \n{part2()}")