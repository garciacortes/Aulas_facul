using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Aula2
{
    public partial class Form1 : Form
    {

        //declarando os atributos da classe Form
        string stringEntrada = string.Empty;
        int quantidadeCaracteresEntrada;
        private char[] charEntrada;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void groupBox2_Enter(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //Recebe entrada
            stringEntrada = TextBoxEntrada.Text;

            //quantidade de caracteres da string de entrada
            quantidadeCaracteresEntrada = stringEntrada.Length;

            //converte string em vetor de caracteres
            charEntrada = stringEntrada.ToCharArray();

            //limpar log
            listBoxLog.Items.Clear();

            //mostrar a entrada no log
            listBoxLog.Items.Add("*** Fita = " + stringEntrada);

            //limpar o texto de entrada
            TextBoxEntrada.Text = "";

            AutomatoFinitoDeterministico automato = new AutomatoFinitoDeterministico();

            bool resultado = automato.Reconhecer(stringEntrada, listBoxLog);

            if(resultado == true)
            {
                labelResposta.Text = "ACEITA";
            }
            else
            {
                labelResposta.Text = "REIJEITA";
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void listBoxLog_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
