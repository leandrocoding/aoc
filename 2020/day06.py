with open("day06.txt", "r") as f:
    inputdata = f.readlines()
theabc = "abcdefghijklmnopqrstuvwxyz"
abc = [0 for i in range(26)]
totsum = 0
for line in inputdata:
    if line == "\n":

        summ = abc.count(1)
        totsum += 26 - summ             #Part 1 without the 26, just + summ
        abc = [0 for i in range(26)]    
    else:
        line = line.rstrip()
        for j in range(26):
            if theabc[j] not in line:   #Part 1 without not.
                abc[j] = 1
summ = 26 - abc.count(1)                #Part 1 without the 26, just + summ
totsum += summ
print(totsum)
       


