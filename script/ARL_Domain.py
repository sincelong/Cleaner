ls = []
with open("1.txt", "r+") as f:
    ls  =list(f)
with open("subdomain.txt", "w+") as f:
    cnt = 0
    for x in ls :
        c = x.split(".")
        len_c = len(c)
        cnt = cnt + 1
        for i in range(0 , len_c):
            if i + 2 < len_c:
                print(c[i])
                f.writelines(c[i]+"%s" % ( "\n" if i+3==len_c else ".")) 
