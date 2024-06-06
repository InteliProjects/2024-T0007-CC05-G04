import csv
# Código que mapeia os dados do arquivo CSV para transformar em comandos de Cypher
# Lista de palavras-chave e seus tipos correspondentes
palavra_tipo_map = {
    "PELOTIZAÇÃO": "Pelotização",
    "PATIO": "Patio",
    "PÁTIO": "Patio",	
    "MINA": "Mina",
    "USINA": "Usina",
    "PORTO": "Porto",
    "FORNECEDOR": "Fornecedor",
    "BRITAGEM": "Britagem",
    "CLIENTE": "Cliente",
}

with open('GrafosData.csv', newline='', encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)

    cypher_queries = []

    for row in reader:
        origem = row['DescricaoOrigem']
        destino = row['DescricaoDestino']

        tipo_origem = None
        tipo_destino = None

        # Verifica se alguma palavra-chave está presente na descrição da origem
        for palavra, tipo in palavra_tipo_map.items():
            if palavra in origem.upper():
                tipo_origem = tipo
                break

        # Verifica se alguma palavra-chave está presente na descrição do destino
        for palavra, tipo in palavra_tipo_map.items():
            if palavra in destino.upper():
                tipo_destino = tipo
                break

        # Se não encontrou nenhum tipo correspondente, define como 'Outro'
        tipo_origem = tipo_origem if tipo_origem else 'Outro'
        tipo_destino = tipo_destino if tipo_destino else 'Outro'

        # Adicionando consulta Cypher para criar nó de origem com o tipo correto
        cypher_queries.append(f"MERGE (o:{tipo_origem} {{nome: '{origem}'}})")

        # Adicionando consulta Cypher para criar nó de destino com o tipo correto
        cypher_queries.append(f"MERGE (d:{tipo_destino} {{nome: '{destino}'}})")

        # Adicionando consulta Cypher para criar relação entre origem e destino
        cypher_queries.append(f"MATCH (o:{tipo_origem} {{nome: '{origem}'}}), (d:{tipo_destino} {{nome: '{destino}'}}) MERGE (o)-[:CONECTA]->(d)")

with open('neo4j.txt', 'w', encoding='utf-8') as file:
    for query in cypher_queries:
        file.write(query + ';\n')