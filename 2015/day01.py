with open("day01.txt", "r") as f:
    inputdata = f.read()
def main():

    cfloor = 0
    i=1
    baseindex = 0
    for elem in inputdata:
        if elem == "(":
            cfloor += 1
        elif elem ==")":
            cfloor -= 1
        if cfloor<0:
            print(i)
            return i
        i+=1
    print(cfloor)

main()