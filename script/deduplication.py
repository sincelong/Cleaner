res =  set()
with open("out.txt", "r+") as f:
    res = set(f)
with open("Dedeup_out.txt", "w+") as f:
    for _ in res:
        f.writelines(_)
