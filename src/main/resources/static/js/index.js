document.addEventListener("DOMContentLoaded", function () {

    // Fetch the graph data from the server
    fetch('http://localhost:8080')
        .then(response => response.json())
        .then(data => {

            // Arrays para armazenar os nós e as arestas
            var visNodes = new vis.DataSet([]);
            var visEdges = new vis.DataSet([]);

            // Iterar sobre os centros de dados recebidos e criar os nós
            data.forEach(item => {
                // Adiciona o nó (centro de dados)
                visNodes.add({
                    id: item.origem,
                    label: 'CentroDados[' + item.origem + ']',
                });

                // Iterar sobre as conexões (vizinhos) de cada centro de dados
                item.vizinhos.forEach(adjacencia => {
                    visEdges.add({
                        from: item.origem,     // Origem da conexão
                        to: adjacencia.destino, // Destino da conexão
                        label: adjacencia.custo.toString(), // Custo da conexão (como label)
                        title: 'Custo: ' + adjacencia.custo // Título para exibir o custo ao passar o mouse
                    });
                });
            });

            // Criar o gráfico com os nós e arestas gerados
            var visContainer = document.getElementById('vis-graph');
            var visData = { nodes: visNodes, edges: visEdges };
            var visOptions = {
                nodes: {
                    shape: 'dot',       // Forma dos nós
                    size: 20            // Tamanho dos nós
                },
                edges: {
                    smooth: false       // Desabilitar suavização das arestas
                }
            };
            new vis.Network(visContainer, visData, visOptions);
        })
        .catch(error => console.error('Erro ao carregar o grafo:', error));

});
