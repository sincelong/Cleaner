lt = []
with open ("1.txt", "r+") as f:
    lt = list(f)
with open("out.txt", "w+") as f:
    for x in lt :
        start = x.find('port')+5
        end = x.find('/')
       # print(start) 
        #print(end)
        #print(x[start:end])
        f.writelines(x[start:end]+"\n")
