# temperature-monitoring-java

Serviço de **Temperature Monitoring** da plataforma JSensors, parte da capacidade de negócio de **Monitoramento de Temperaturas**.

## Descrição

Responsável pela análise dos dados e geração de relatórios, incluindo armazenamento de histórico, consulta de temperaturas e configuração/disparo de alertas.

## Funcionalidades Principais

- Armazenamento dos dados dos sensores
- Permitir consulta ao histórico de temperatura
- Configuração e disparo de alertas de temperatura

## Endpoints

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/api/sensors/{sensorId}/temperatures` | Lista de registro de temperaturas |
| `PUT` | `/api/sensors/{sensorId}/alert` | Atualiza configuração de alerta de temperatura |
| `GET` | `/api/sensors/{sensorId}/alert` | Consulta configuração de alerta de temperatura |
| `DELETE` | `/api/sensors/{sensorId}/alert` | Deleta configuração de alerta de temperatura |
| `GET` | `/api/sensors/{sensorId}/monitoring` | Detalhes do monitoramento |
| `PUT` | `/api/sensors/{sensorId}/monitoring/enable` | Ativa monitoramento |
| `DELETE` | `/api/sensors/{sensorId}/monitorig/enable` | Inativa monitoramento |

## Objetos de Negócio

### Temperature Log

| Propriedade | Tipo |
| --- | --- |
| Id | UUID |
| SensorId | TSID |
| Registred At | OffsetDateTime |
| Value | Double |

### Sensor Monitoring

| Propriedade | Tipo |
| --- | --- |
| Id | TSID |
| Last Temperature | Double |
| Updated At | OffsetDateTime |
| Enabled | Boolean |

### Sensor Alert

| Propriedade | Tipo |
| --- | --- |
| Id | TSID |
| MaxTemperature | Double |
| MinTemperature | Double |

## Tecnologias Utilizadas

- Linguagem de Programação: Java
- Banco de Dados: Postgres

## Comunicação com Outros Serviços

- **Assíncrona (mensageria, ex.: RabbitMQ)**: Consome as mensagens de novos dados de temperatura publicadas pelo Temperature Processing Service, além de notificar usuários em tempo real quando limites de alerta são atingidos.
- **Síncrona (HTTP/REST)**: Recebe requisições do Device Management Service para desativar o monitoramento caso um sensor seja desativado.

## Papel na Solução

Este serviço resolve os problemas de **falta de alertas de oscilações de temperatura** e **falta de histórico de temperaturas**: configura e dispara alertas com base em valores máximos e mínimos definidos para cada sensor, e armazena os dados recebidos do Temperature Processing Service em um banco de dados temporal, permitindo consulta de histórico por longos períodos e análise de tendências.
