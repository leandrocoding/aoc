import  hashlib

inputdata = "yzbqklnj"




# print(res.hexdigest())
def main():

    for i in range(10000000):
        tohash = inputdata + str(i)
        res = hashlib.md5(tohash.encode())
        isvalid = True
        hexval = str(res.hexdigest())
        for j in range(6):
            if hexval[j] != "0":
                isvalid = False
                # print("Hey")
                continue
        if isvalid:
            print(hexval)
            print(i)
            return i
            

main()