with open("day02.txt", "r") as f:
    inputdata = f.readlines()
totalArea = 0
totalrib = 0
for line in inputdata:
    line = line.rstrip()
    dims = line.split("x")
    l = int(dims[0])
    w = int(dims[1])
    h = int(dims[2])
    a1 = l*w
    a2 = h*w
    a3 = l*h
    smallen = sorted([l, w, h])
    riblen = 2 * smallen[0] + 2 * smallen[1] + l*w*h
    smallstA = sorted([a1, a2, a3])[0]
    curtA = 2*(a1+a2+a3) + smallstA
    totalArea += curtA
    totalrib += riblen
print(totalArea)
print(totalrib)
    
    

