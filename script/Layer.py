ls = []
with open("1.txt", "r+", encoding="utf-8") as f:
    ls = list(f)
ls.remove(ls[0])
with open("tmp.txt", "w+") as f:
    for x in ls :
        # print(x.find("\t"))
        # print(x[:x.find("\t")])
        f.writelines(x[:x.find('\t')]+"\n")
