import os
path = os.path.join(os.path.dirname(__file__), 'day03.txt')

with open(path) as f:
    inputdata = list(map(lambda x: list(x.rstrip()), f.readlines()))

def p1():
    numofbits = len(inputdata[0])
    res1 = ""
    res2 = ""
    for bitN in range(numofbits):
        countones = 0
        for elem in inputdata:
            if elem[bitN] == "1":
                countones+=1
    
        if countones > len(inputdata)/2:
            res1+= "1"
            res2+= "0"
        else:
            res1 += "0"
            res2 += "1"
    return (res1, res2)

def get_ratings(curlist, bitN, takepopular):
    
    newlist = []
    listOnes = []
    listZero = []

    for element in curlist:
        if element[bitN] == "1":
            listOnes.append(element)
        else:
            listZero.append(element)
    if takepopular:

        if len(listOnes)>=len(listZero):
            newlist = list(listOnes)
        else:
            newlist = list(listZero)
    
    else:

        if len(listOnes)<len(listZero):
            newlist = list(listOnes)
        else:
            newlist = list(listZero)


    if len(newlist)<=1:
        return newlist
    else:
        # print(bitN)
        return get_ratings(newlist,bitN+1, takepopular)
        

def main():
    # Part 1
    p1Bin1, p1Bin2 = p1()
    resP1 = int(p1Bin1,2)*int(p1Bin2,2)
    print(f"Part 1: {resP1}")

    # Part 2

    oxyBin = "".join(get_ratings(list(inputdata),0,True)[0])
    oxyDec = int(oxyBin,2)
   
    co2Bin = "".join(get_ratings(list(inputdata),0,False)[0])
    co2Dec = int(co2Bin,2)
    print(f"Part 2: {oxyDec*co2Dec}")

main()