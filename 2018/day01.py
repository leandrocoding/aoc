import os
path = os.path.join(os.path.dirname(__file__), 'day01.txt')

with open(path) as f:
    inputdata = f.readlines()

def part1():
    total = 0
    freqlist = {}
    for line in inputdata:
        total += int(line)
    return total

def part2():
    total = 0
    freqlist = set()
    while True:
        for line in inputdata:
            total += int(line)
            if total in freqlist:
                return total
            freqlist.add(total)

print(f"\nAOC 2018 Day 01: \n")
print(f"Part 1: {part1()}")
print(f"Part 2: {part2()}")