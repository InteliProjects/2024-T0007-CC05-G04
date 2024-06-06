import json
import pandas as pd
import os

index = 1
exports = 0
fileNames = []

# Começa a conversão de dados para csv
def start():
    if not os.path.exists("./csvs"):
        os.mkdir("./csvs")
    file = open("./sistemaSudeste.json").read()
    obj = json.loads(file)["cenario"] 
    
    for i in obj:
        tryGetCsv(i, obj[i], "cenarioId")
    
    print("Exported " + str(exports) + " files.")


# Tenta extrair uma tabela csv recursivamente verificando se o objeto em análise é uma tabela ou um conjunto de tabelas
def tryGetCsv(tag : str, obj, key : str):
    if not isList(obj):
        if not isDict(obj):
            return
        gotCsv = False
        aux = obj.copy()
        for i in obj:
            if canGetCsv(obj[i]) and not gotCsv:
                deleteDictsOrLists(aux)
                insertKey(aux, key)
                gotCsv = True
                getCsv(tag + "_" + key.removesuffix("Id"), aux)
            else:
                tryGetCsv(i, obj[i], tag + "Id")

    else:
        getColumnsFromDictsWithinObj(tag, obj)
        getCsv(tag, obj)


# Verifica se o objeto é um dicionário
def isDict(obj):
    return isinstance(obj, dict)


# Verifica se o objeto é uma lista
def isList(obj):
    return isinstance(obj, list)


# Verifica se o objeto é uma tabela válida que pode ser transformada em arquivo csv
def canGetCsv(obj):
    return not isDict(obj) and obj is not None and not isList(obj)


# Deleta subtabelas antes de exportar csv's de tabelas principais, pois as subtabelas serão exportadas a parte
def deleteDictsOrLists(obj):
    containsDict = [key for key in obj.keys() if isDict(obj[key]) or isList(obj[key])]
    if not len(containsDict):
        return
    for i in containsDict:
        del obj[i]


# Insere uma key para identificação posterior no tratamento dos dados
def insertKey(obj, key : str):
    global index
    obj[key] = index
    index += 1


# Adiciona colunas de subtabela à tabela principal se for possível (um nível de aninhamento)
def getColumnsFromDictsWithinObj(tag, obj):
    for i in range(len(obj)):
        if not isDict(obj[i]):
            continue
        dic = obj[i]
        aux = dic.copy()
        for j in dic.keys():
            if isDict(dic[j]):
                tryGetCsv(j, dic[j], tag + "Id")
                del aux[j]
                continue
        obj[i] = aux


# Decide se exporta ou se sobrescreve um arquivo existente
def getCsv(fileName, content):
    global fileNames

    if fileName in fileNames:
        overwrite(fileName, content)

    else:
        fileNames.append(fileName)
        export(fileName, content)


# Exporta arquivo csv
def export(fileName, content):
    global exports
    
    df : pd.DateFrame

    if (isDict(content)):
        df = pd.DataFrame.from_dict([content])
    
    else:
        df = pd.DataFrame.from_dict(content)

    df.to_csv("./csvs/" + fileName + ".csv", index=False)
    exports += 1


# Sobrescreve arquivo csv existente
def overwrite(fileName, content):
    file = pd.read_csv("./csvs/" + fileName + ".csv")

    df : pd.DataFrame

    if isDict(content):
        df = pd.DataFrame.from_dict([content])

    elif fileName == "idElo":
        idEloDict = {"idElo": content}
        df = pd.DataFrame.from_dict([idEloDict])
    else:
        df = pd.DataFrame.from_dict(content)
    
    pd.concat([df, file]).to_csv("./csvs/" + fileName + ".csv", index=False)