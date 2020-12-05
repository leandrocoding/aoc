with open("day03.txt", "r") as f:
# with open("test.txt", "r") as f:

    lines = f.readlines()
grid = []
for line in lines:
    list1=[] 
    list1[:0]=line 
    if "\n" in list1:
        list1.remove("\n")
    grid.append(list1)
    


def main(slope):
    gr = grid[1]
    print(gr)
    width = len(gr)
    height = len(grid)
    currheight = 0

    
    count = 0
    pointerx = 0
    pointery = 0
    while pointery < (height):
        pointerx += slope[0]
        pointery += slope[1]
        if pointery>=height:
            print(count)
            return count
        if pointerx>=width:

            pointerx -=width
            
        
        if grid[pointery][pointerx] =="#":
            count+=1
    print(count)
    return count
        
            

totco = 1
totco *= main((1,1))
totco *= main((3,1))
totco *= main((5,1))
totco *= main((7,1))
totco *= main((1,2))
print(totco)

