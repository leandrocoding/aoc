
with open("day07.txt", "r") as f:
    inputdata = f.readlines()

bagsdict = {}

count = 0
def finddict():
    for line in inputdata:
        line = line.rstrip()
        words = line.split(" ")
        bagname = words[0]+words[1]
        bags = []
        bagcountlocal = line.count("bag")-1
        startindex = 4
        if words[startindex] == "no":
            bagsdict[bagname] = [("",0)]
            bagcountlocal = 0
            continue
        for i in range(bagcountlocal):
            currindex = i*4 + startindex
            bagammount = int(words[currindex])
            innerBagName = words[currindex+1]+words[currindex+2]
            bags.append((innerBagName,bagammount))
            
        bagsdict[bagname] = bags

def mainshiny():
    count = -1
    done = 0
    for bagcol in bagsdict:
        done +=1
        
        if contshiny(bagcol):
            count +=1

        print(f"Done: {done}")

    print(count)

def contshiny(color):
    if color == "shinygold":
        return True
    if color == "":
        return False
    else:
        return any(contshiny(innercol[0]) for innercol in bagsdict[color])



def countbags(color):
    if color == "":
        return 1
   
    summ = 1
    for elem in bagsdict[color]:
        print(elem)
        summ += elem[1] * countbags2(elem[0])
        print(summ)
    return summ
        


finddict()
# Part 1
print(sum(contshiny(color) for color in bagsdict.keys())-1)
# Part 2
print(countbags("shinygold")-1)

            
    

      

    


    

