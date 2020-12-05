with open("day04.txt", "r") as f:
    inputdata = f.readlines()
count = 0
byr = False
iyr = False
eyr = False
hgt = False
hcl = False
ecl = False
pid = False
cid = False



# def checksegs(line):
    # segments = line.split(" ")
    # for seg in segments:
    #     minseg = seg.split(":")
#         try:
#             pass
#         except e:

#             byr = False
#             iyr = False
#             eyr = False
#             hgt = False
#             hcl = False
#             ecl = False
#             pid = False
#             cid = False
#         # if minseg[0] == "byr" and minseg[1] 

for line in inputdata:
    # print(line)
    if line == "\n":
        # print("New line")
        print(str(byr) + str(iyr)  + str(eyr)  + str(hgt)  + str(hcl)  + str(ecl)  + str(pid))
        byr = False
        iyr = False
        eyr = False
        hgt = False
        hcl = False
        ecl = False
        pid = False
        cid = False
        print("------------------------")
    else:

        # checksegs(line)
        segments = line.rstrip().split(" ")
        try:
            for seg in segments:
                minseg = seg.split(":")
                
                if minseg[0] == "byr":
                    print(minseg)
                    if 1920 <= int(minseg[1]) <= 2002:
                        byr = True
                        
                    else:
                        print("byr Flase")

                elif minseg[0] == "iyr":
                    print(minseg)
                    if 2010 <= int(minseg[1]) <= 2020:
                        iyr = True
                    else:
                        print("iyr Flase")
                elif minseg[0] == "eyr":
                    print(minseg)
                    if 2020 <= int(minseg[1]) <= 2030:
                        eyr = True
                    else:
                        print("eye Flase")
                elif minseg[0] == "hgt":
                    print(minseg)
                    if "cm" in minseg[1]:
                        if 150 <= int(minseg[1][:-2]) <= 193:
                            hgt = True
                        else: 
                            print("cm Flase")
                    elif "in" in minseg[1]:
                        if 59 <= int(minseg[1][:-2]) <= 76:
                            hgt = True
                        else: 
                            print("in Flase")
                    else:
                        # hgt = False
                        print("Tot False")
                elif minseg[0] == "hcl":
                    print(minseg)
                    if minseg[1][0] == "#":
                        
                        if  0< int(minseg[1].strip("#"), 16) < int("ffffff",16):
                            hcl = True
                        else: 
                            print("False hcl")
                
                elif minseg[0] == "ecl":
                    print(minseg)
                    if "amb" in minseg[1] or "blu" in minseg[1] or "brn" in minseg[1] or "gry" in minseg[1] or "grn" in minseg[1] or "hzl" in minseg[1] or "oth" in minseg[1]:
                        ecl = True
                    else:
                        print("false ecl")
                elif minseg[0] == "pid":
                    print(minseg)
                    if len(minseg[1]) == 9 and int(minseg[1]):
                        pid = True
                    else:
                        print("pid false")

                



        except:
            print("Error")

        # if "byr" in line:
        #     byr = True
        # if "iyr" in line:
        #     iyr = True
        # if "eyr" in line:
        #     eyr = True
        # if "hgt" in line:
        #     hgt = True
        # if "hcl" in line:
        #     hcl = True
        # if "ecl" in line:
        #     ecl = True
        # if "pid" in line:
        #     pid = True
        # if "cid" in line:
        #     cid = True
    if byr and iyr and eyr and hgt and hcl and ecl and pid:
        print("Heryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
        count +=1
        byr = False
        iyr = False
        eyr = False
        hgt = False
        hcl = False
        ecl = False
        pid = False
        cid = False



print(count)


