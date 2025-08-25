using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aula2
{
    internal class Estado
    {
        private bool EstadoFinal;

        //tabela de transição de estado
        Dictionary<char, string> Transicoes;

        //construtor
        public Estado(bool TipoEstado)
        {
            this.EstadoFinal = TipoEstado;

            Transicoes = new Dictionary<char, string>();
        }

        //Retorna se é o estado final ou não
        public bool EhEstadoFinal()
        { 
            return EstadoFinal; 
        }

        public void AdicionarTransicao(char SimboloLido, string EstadoTransicao)
        {
            Transicoes.Add(SimboloLido, EstadoTransicao);
        }

        //Retornar o novo estado da transição a partir do simbolo lido
        public string RetornarNovoEstadoTransicao(char SimboloLido)
        {
            if(Transicoes.ContainsKey(SimboloLido))
            {
                return Transicoes[SimboloLido];
            } 
            else
            {
                return null;
            }
        }
    }
}
