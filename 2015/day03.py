with open("day03.txt", "r") as f:
    inputdata = f.read()

posx = 0
posy = 0
posx2 = 0
posy2 = 0
visitedpos = [(0, 0)]
visitedpos2 = [(0, 0)]
i = 0
for ins in inputdata:
    
    if i % 2 == 0:

        if ins == "^":
            posy += 1
        elif ins == "v":
            posy -= 1
        elif ins == ">":
            posx += 1
        elif ins == "<":
            posx -= 1
        if (posx, posy) not in visitedpos:
        
            visitedpos.append((posx, posy))
    else:
        if ins == "^":
            posy2 += 1
        elif ins == "v":
            posy2 -= 1
        elif ins == ">":
            posx2 += 1
        elif ins == "<":
            posx2 -= 1
        if (posx2, posy2) not in visitedpos:
        
            visitedpos.append((posx2, posy2))
    i+=1




    

print(len(visitedpos))
# print(len(visitedpos2))

