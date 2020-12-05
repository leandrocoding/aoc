

with open("day1.txt", "r") as f:
    expenses = f.readlines()

for i in expenses:
    for j in expenses:
        for k in expenses:

            # print(int(i))
            # print(int(j))
            summ=int(i)+int(j)+int(k)
            # if summ 
            # print(summ)
            if (summ)==2020:
                print(int(i)*int(j)*int(k))