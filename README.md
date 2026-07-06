# Cartão Viagem - Sistema de Gerenciamento

Este projeto implementa um sistema de gerenciamento de cartões de viagem multimoeda. O sistema permite que usuários realizem cargas pagando em Real Brasileiro (BRL) e recebam créditos em moedas estrangeiras de sua escolha, utilizando taxas de conversão em tempo real obtidas via API externa.

Principais funcionalidades:

- **Cargas Multimoeda**: Converta Reais para USD, EUR, JPY, GBP e outras moedas internacionais
- **Saques Multimoeda**: Converta saldos de moedas estrangeiras de volta para reais
- **Taxas Atualizadas**: Cotações em tempo real via AwesomeAPI para garantir conversões precisas
- **Gestão Completa**: Criação, consulta e cancelamento de cartões com validações de segurança

## Regras de Negócio

### Cartão

- Cada cartão possui número único, nome do titular, CPF, senha e status
- **Numeração**: Cartões são gerados automaticamente com 16 dígitos únicos
- Status possíveis: ATIVO, BLOQUEADO, CANCELADO
- Apenas cartões ATIVOS podem realizar operações financeiras
- Cada cartão mantém saldos separados por moeda (multimoedas)

### Operações Financeiras

- **Carga**: Crédito de valor Real (BRL) convertido para moeda estrangeira escolhida
- **Saque**: Débito de moeda estrangeira convertido para Real (BRL)
- **Limite**: Carga máxima de R$ 10.000,00 por transação de carga
- **Saldo**: Controle independente por moeda (USD, EUR, JPY, GBP, etc.)
- **Conversão**: Taxas de câmbio em tempo real via AwesomeAPI

### Como Funciona o Negócio

O sistema permite que o usuário carregue seu cartão em diferentes moedas estrangeiras:

1. **Carga Multimoeda**: O usuário informa o valor em Reais e a moeda desejada (USD, EUR, etc.)
2. **Saque Multimoeda**: O usuário informa o valor da moeda desejada (USD, EUR, etc.)
3. **Conversão Automática**: O sistema consulta a AwesomeAPI para obter a taxa de câmbio atual
4. **Saldo Separado**: Cada moeda tem seu próprio saldo no cartão
5. **Exemplo**:
   - Usuário carrega R$ 5.000 em USD (taxa 5,25): recebe $952,38
   - Usuário carrega R$ 3.000 em EUR (taxa 5,75): recebe €521,74
   - Cartão fica com dois saldos: $952,38 e €521,74

### Cancelamento

- Pedido de cancelamento é feito informando número de cartão, senha e CPF.
- Validação de CPF, senha e número do cartão
- Cartão cancelado não pode ser cancelado novamente
- Apenas cartões ATIVOS podem ser cancelados

## Documentação da API

### Base URL

```
/cartao
```

### Endpoints

#### 1. Criar Cartão

```
POST /cartao
```

**Request:**

```json
{
  "nomeCompleto": "João Silva",
  "cpf": "12345678901",
  "senha": "123456"
}
```

**Response:**

```json
{
  "numeroCartao": "1234567890123456",
  "mensagem": "Cartão criado com sucesso"
}
```

#### 2. Consultar Cartão

```
GET /cartao/{numeroCartao}
```

**Response:**

```json
{
  "numeroCartao": "1234567890123456",
  "nomeTitular": "João Silva",
  "status": "ATIVO",
  "dataEmissao": "2025-10-13T10:00:00",
  "saldos": {
    "USD": 1500.00,
    "EUR": 800.50
  }
}
```

#### 3. Carregar Cartão

```
POST /cartao/carga
```

**Request:**

```json
{
  "numeroCartao": "1234567890123456",
  "moedaCarga": "USD",
  "valorCargaRealBrasileiro": 1000.00
}
```

**Response:**

```json
{
  "numeroCartao": "1234567890123456",
  "moeda": "USD",
  "valorCargaRealBrasileiro": 1000.00,
  "taxaConversao": 0.1922,
  "valorCargaMoedaSaidaConversao": 192.20,
  "saldoRestante": 692.20,
  "mensagem": "Carga realizada com sucesso"
}
```

#### 4. Sacar do Cartão

```
POST /cartao/saque
```

**Request:**

```json
{
  "numeroCartao": "1234567890123456",
  "moedaSaque": "USD",
  "valorSaqueMoedaEstrangeira": 200.00
}
```

**Response:**

```json
{
  "numeroCartao": "1234567890123456",
  "moeda": "USD",
  "valorSaqueMoedaEstrangeira": 200.00,
  "taxaConversao": 5.20,
  "valorSaqueRealBrasileiro": 1040.00,
  "saldoRestante": 300.00,
  "mensagem": "Saque realizado com sucesso"
}
```

#### 5. Cancelar Cartão

```
POST /cartao/cancelamento
```

**Request:**

```json
{
  "numeroCartao": "1234567890123456",
  "cpfTitular": "12345678901",
  "senha": "123456"
}
```

**Response:**

```json
{
  "numeroCartao": "1234567890123456",
  "status": "CANCELADO",
  "mensagem": "Cartão cancelado com sucesso"
}
```

##  API Externa

### AwesomeAPI

Utilizamos a **AwesomeAPI** para conversão de moedas em tempo real, uma API pública brasileira que fornece cotações atualizadas do mercado financeiro.

**URL da API:** `https://economia.awesomeapi.com.br/json/last/{moedaOrigem}-{moedaDestino}`

**Documentação:** https://docs.awesomeapi.com.br/api-de-moedas

**Finalidade:**

- Obter taxas de câmbio atualizadas do mercado financeiro
- Suporte a múltiplas moedas (USD, EUR, JPY, GBP, CAD, AUD, etc.)
- Conversão automática de Reais para moedas estrangeiras e vice-versa
- Garantir cotações precisas para operações financeiras

**Exemplo de Utilização:**

```http
GET https://economia.awesomeapi.com.br/json/last/BRL-USD
```

**Resposta da API:**

```json
{
  "BRLUSD": {
    "code": "BRL",
    "codein": "USD", 
    "name": "Real Brasileiro/Dólar Americano",
    "high": "0.1925",
    "low": "0.1918",
    "varBid": "0.0003",
    "pctChange": "0.17",
    "bid": "0.1922",
    "ask": "0.1923",
    "timestamp": "1697198400",
    "create_date": "2023-10-13 15:20:00"
  }
}
```

**Como é utilizado:**

1. **Carga**: Usuário quer carregar R$ 1.000 em Dólares
2. **Consulta**: Sistema chama `GET /json/last/BRL-USD` na AwesomeAPI
3. **Conversão**: Com taxa "bid": "0.1922", usuário recebe $192,20 no cartão
4. **Saque**: Para saque USD→BRL, chama `GET /json/last/USD-BRL` e usa a taxa para converter

**Implementação:**

- Cliente Feign para comunicação HTTP com a API
- Normalização de códigos de moeda (ex: dolar -> USD)
- Consulta em tempo real das taxas de câmbio

**Moedas Suportadas:**
USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, ARS, CLP e outras principais moedas globais.
### Passos para Execução

**1. Build do Projeto Multi-módulo (Pasta Pai):**

```bash
# Na pasta raiz do projeto (onde está o pom.xml pai)
mvn clean install
mvn spring-boot:run
```

# Executar o Spring Boot

```

A aplicação estará disponível em: http://localhost:8080

### Testando a API

Para facilitar os testes da API, utilize a collection do Postman disponível no arquivo: