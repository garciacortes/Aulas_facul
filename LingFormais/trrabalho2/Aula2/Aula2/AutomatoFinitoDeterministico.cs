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
            q0.AdicionarTransicao('1', "q2");
            q0.AdicionarTransicao('0', "q1");
            TabelaDeTransicao.Add("q0", q0);

            Estado q1 = new Estado(false);
            q1.AdicionarTransicao('1', "q0");
            q1.AdicionarTransicao('0', "q3");
            TabelaDeTransicao.Add("q1", q1);

            Estado q2 = new Estado(false);
            q2.AdicionarTransicao('0', "q3");
            q2.AdicionarTransicao('1', "q2");
            TabelaDeTransicao.Add("q2", q2);

            Estado q3 = new Estado(false);
            q3.AdicionarTransicao('0', "q4");
            q3.AdicionarTransicao('1', "q2");
            TabelaDeTransicao.Add("q3", q3);

            Estado q4 = new Estado(false);
            q4.AdicionarTransicao('1', "q5");
            q4.AdicionarTransicao('0', "q4");
            TabelaDeTransicao.Add("q4", q4);

            Estado q5 = new Estado(true);
            q5.AdicionarTransicao('1', "q5");
            q5.AdicionarTransicao('0', "q5");
            TabelaDeTransicao.Add("q5", q5);

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
