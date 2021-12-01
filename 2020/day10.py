import os

path = os.path.join(os.path.dirname(__file__), 'day10.txt')

with open(path, "r") as f:
    inputdata = f.readlines()

numberlist = sorted([0] + list(map(lambda x: int(x.rstrip()), inputdata)))
numberlist.append(max(numberlist)+3)

# Part 1
diff = []

l2 = numberlist[1:]
for i in range(len(l2)):
    diff.append(l2[i]- numberlist[i])
one = diff.count(1)
three = diff.count(3)
print(f"Result: {one * three}")

# Part 2

print(numberlist)
for num in numberlist:
    
     
