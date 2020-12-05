with open("day05.txt", "r") as f:
    inputdata = f.readlines()
with open("test.txt", "r") as f:
    testinput = f.readlines()

count = 0
nas = ["ab", "cd", "pq", "xy"]
# for line in testinput:
for line in inputdata:

    # part2(line)
    # print(line)
    letterinrow = False
    twost = False
    for j in range(len(line) - 2):
        if line[j] == line[j + 2]:
            letterinrow = True
            continue


    for k in range(len(line)-1):
        if line[k:k+2] in line[k+2:len(line)]:
            twost = True
            continue

    if letterinrow and twost:
        count += 1
    
print(count)



# in for loop for part 1
# letterinrow = False
# if any([sub in line for sub in nas]):
#     continue

# for j in range(len(line)-1):
#     if line[j]==line[j-1]:
#         letterinrow = True
#         continue
    
# if line.count("a") + line.count("e") + line.count("i") + line.count("o") + line.count("u") < 3:
#     continue
    
# if letterinrow:
#     count += 1

