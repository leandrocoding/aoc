with open("day2.txt", "r") as f:
    lines = f.readlines()

count = 0

for line in lines:
    linesegs = line.split(" ")

    # Bounds
    bounds = linesegs[0].split("-")
    lowbound = int(bounds[0])
    upbound = int(bounds[1])

    # Letter
    checklett = linesegs[1][0]
    # print(checklett)

    # Passs 
    pasw = linesegs[2]
    loccount = pasw.count(checklett)
    # if lowbound <= loccount <= upbound:
    #     count += 1
    #     print(line)
    firstpos = pasw[lowbound-1] == checklett
    secpos = pasw[upbound-1] == checklett
    if firstpos!=secpos:
        count+=1
        print(line)
print("----------")
print(count)
    
    

