import math

with open("day05.txt", "r") as f:
    inputdata = f.readlines()
STARTROWS = 128
STARTCOLLS = 8

def findseat(boardpass):
    collims = [0, STARTCOLLS-1]
    rowlims = [0, STARTROWS-1]
    for letter in boardpass:
        # print(f"L: {letter} R: {rowlims} C: {collims}")
        rowmid = (rowlims[0] + rowlims[-1])/2
        colmid = (collims[0] + collims[-1])/2
        if letter=="B":
            rowlims[0] = math.ceil(rowmid)
        if letter=="F":
            rowlims[1] = math.floor(rowmid)
        if letter=="R":
            collims[0] = math.ceil(colmid)
        if letter=="L":
            collims[1] = math.floor(colmid)
    return rowlims[0], collims[0]


def main():

    toppass = 0
    passes = [False for i in range(1000)]

    for line in inputdata:
        r,c = findseat(line)
        res = r*8+c
        # print(line)
        # print(f"R: {r} C: {c} Summ: {res}")
        # passes.append(res)
        passes[res]=True
        if res>toppass:
            toppass = res
    
    for i in range(2,996):
        if passes[i-1] and passes[i+1] and not passes[i]:
            print(i)
            return i



    # print(toppass)

    
main()
