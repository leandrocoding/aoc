import os
path = os.path.join(os.path.dirname(__file__), 'day02.txt')

with open(path) as f:
    inputdata = f.readlines()

DOWN = "down"
UP = "up"
FW = "forward"

# Part 1
posxp1 = 0
depthp1 = 0

# Part 2
# Aim is the same as depth from Part 1
# Posx is the same as in Part 1
depthp2 = 0


for line in inputdata:
    instr = line.split()
    if instr[0] == DOWN:
        depthp1 += int(instr[1])
        continue

    if instr[0] == UP:
        depthp1 -= int(instr[1])
        continue

    if instr[0] == FW:
        posxp1 += int(instr[1])
        depthp2 += depthp1*int(instr[1])
        continue

    print("WTF?")
print("\nAOC 2021 Day 02: \n")
print(f"Part 1: \nDepth: {depthp1} \nPosition: {posxp1} \nResult: {depthp1*posxp1} \n" )
print(f"Part 2: \nDepth: {depthp2} \nPosition: {posxp1} \nResult: {depthp2*posxp1} \n" )
