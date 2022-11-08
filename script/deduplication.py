res =  set()
with open("1.txt", "r+") as f:
    res = set(f)
with open("Dedeup_phone.txt", "w+") as f:
    for _ in res:
        f.writelines(_)
