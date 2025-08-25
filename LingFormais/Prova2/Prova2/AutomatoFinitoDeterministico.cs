using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Aula2
{
    internal class AutomatoFinitoDeterministico
    {
        //tabela Hash
        private Dictionary<string, Estado> TabelaDeTransicao;

        public AutomatoFinitoDeterministico()
        {
            TabelaDeTransicao = new Dictionary<string, Estado>();

            Estado q0 = new Estado(false);
            for(char i  = 'a'; i <= 'z';  i++)
            {
                q0.AdicionarTransicao(i, "q1");
                q0.AdicionarTransicao(char.ToUpper(i), "q1");
            }
            q0.AdicionarTransicao('_', "q1");
            TabelaDeTransicao.Add("q0", q0);

            Estado q1 = new Estado(true);
            for (char i = 'a'; i <= 'z'; i++)
            {
                q1.AdicionarTransicao(i, "q1");
                q1.AdicionarTransicao(char.ToUpper(i), "q1");
            }
            for (char i = '0'; i <= '9'; i++)
            {
                q1.AdicionarTransicao(i, "q1");
            }
            q1.AdicionarTransicao('_', "q1");
            TabelaDeTransicao.Add("q1", q1);
        }

        public bool Reconhecer(String palavra, ListBox log)
        {
            string EstadoAtual = "q0"; //Setar estado inicial
            //pegar tamanho da palavra
            int TamanhoDaPalavra = palavra.Length;
            //converte string em vetor de caracteres
            char[] charEntrada = palavra.ToCharArray();

            for (int indice = 0; indice < TamanhoDaPalavra; indice++)
            {
                //montar o texto log de execução do automato    
                string textolog;
                textolog = "Estado Atual = " + EstadoAtual + "-- Simbolo Lido = " + charEntrada[indice];

                //verificar a transição para o novo estado a partir do simbolo lido
                EstadoAtual = TabelaDeTransicao[EstadoAtual].RetornarNovoEstadoTransicao(charEntrada[indice]);

                //se não retornar nenhum estado, eh porque o automato não tem leitura de um determinado simbolo naquele estado
                if (EstadoAtual == null)
                {
                    textolog += "--> NÃO VAI PARA NENHUM ESTADO";
                    log.Items.Add(textolog);
                    return false;
                }
                else
                {
                    //montar o texto log de execução do automato
                    textolog += "--> vai para " + EstadoAtual;
                    log.Items.Add(textolog);
                }
            }

            //se finalizou no estado final, a palavra é aceita
            if (TabelaDeTransicao[EstadoAtual].EhEstadoFinal())
            {
                //montar o texto do log de execução do automato
                log.Items.Add("ESTADO FINAL");
                return true;
            }
            else
            {
                //montar o texto do log de execução do automato
                log.Items.Add("NÃO É ESTADO FINAL");
                return false;
            }
        }
    }
}
