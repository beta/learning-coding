using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Slex {
    public partial class MainForm : Form {

        private String[] keywords = {
            "auto",     "break",    "case",     "char",
            "const",    "continue", "default",  "do",
            "double",   "else",     "enum",     "extern",
            "float",    "for",      "goto",     "if",
            "int",      "long",     "register", "return",
            "short",    "signed",   "sizeof",   "static",
            "struct",   "switch",   "typedef",  "union",
            "unsigned", "void",     "volatile", "while"
        };

        private String[] operators = {
            "=", "+", "-", "*", "/", "%", "++", "--",
            "==", "!=", ">", "<", ">=", "<=",
            "!", "&&", "||",
            "~", "&", "|", "^", "<<", ">>",
            "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=",
            "[", "]", "->", ".",
            "(", ")", ",", "?", ":",
            ";", "{", "}"
        };

        private String fileName;
        private String[] fileContent;
        private List<String> symbols = new List<string>();

        private Boolean isComment = false;

        public MainForm() {
            InitializeComponent();
        }

        private void buttonBrowse_Click(object sender, EventArgs e) {
            DialogResult result = openFileDialog.ShowDialog();
            if (result == DialogResult.OK) {
                // Open file dialog to select a file.
                this.fileName = openFileDialog.FileName;
                textBoxFileName.Text = this.fileName;

                this.fileContent = File.ReadAllLines(this.fileName);
                textBoxSource.Text = this.fileContent.ToString();
                textBoxSource.Clear();
                foreach (String line in fileContent) {
                    textBoxSource.AppendText(line + Environment.NewLine);
                }
                
                buttonStart.Enabled = true;
            }
        }

        private void buttonStart_Click(object sender, EventArgs e) {
            for (int i = 0; i < fileContent.Length; i++) {
                ParseLine(fileContent[i]);
            }
        }

        private void ParseLine(String line) {
            if (line.StartsWith("#")) {
                // Preprocessor.
                // Drop this line, as it's not a real C compiler.
                return;
            }

            String newLine = "";
            
            for (int i = 0; i < line.Length; i++) {
                Char c = line[i];

                if (c == '/' && line[i + 1] == '/') {
                    // Comment.
                    // Drop the rest of the line.
                    return;
                }

                if (i < line.Length - 1 && c == '/' && line[i + 1] == '*') {
                    // Multi-line comment starts.
                    isComment = true;
                    i++;
                    continue;
                }

                if (i < line.Length - 1 && c == '*' && line[i + 1] == '/') {
                    // Multi-line comment ends.
                    isComment = false;
                    i++;
                    continue;
                }

                if (isComment) {
                    // If a multi-line comment hasn't ended, skip the current character.
                    continue;
                }

                if (IsLetter(c) || c == '_') { // Identifier or keyword.
                    String symbol = "";
                    while (i < line.Length && IsLetter(line[i]) || IsDigit(line[i]) || line[i] == '_') {
                        symbol += line[i++];
                    }
                    i--;

                    if (IsKeyword(symbol)) {
                        // Keyword.
                        // Directly add it to the output stream.
                        newLine += symbol;
                    } else {
                        // Identifier.
                        // Add it to the symbol table and insert a token
                        // into the output stream.
                        int symbolId = symbols.IndexOf(symbol);
                        if (symbolId < 0) {
                            symbols.Add(symbol);
                            symbolId = symbols.IndexOf(symbol);

                            listSymbols.Items.Add(
                                new ListViewItem(new[] { symbolId.ToString(), symbol })
                            );
                        }

                        newLine += "<id, " + symbolId + ">";
                    }
                } else if (IsDigit(c)) { // Number.
                    String num = "";
                    while (i < line.Length && IsDigit(line[i])) {
                        num += line[i++];
                    }
                    i--;

                    newLine += "<constant, " + num + ">";
                } else if (c == '"' || c == '\'') { // String constant or character constant.
                    String s = "";
                    i++;
                    while (i < line.Length && line[i] != c) {
                        s += line[i++];
                    }
                    newLine += "<constant, \"" + s + c + ">";
                } else if (IsOperator(c.ToString())) { // Operator.
                    newLine += c;
                } else if (c == ' ') {
                    while (i < line.Length && line[i] == ' ') {
                        i++;
                    }
                    i--;
                    newLine += " ";
                }
            }

            textBoxOutput.AppendText(newLine + Environment.NewLine);
        }

        private Boolean IsDigit(Char c) {
            return (c >= '0' && c <= '9');
        }

        private Boolean IsLetter(Char c) {
            return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }

        private Boolean IsKeyword(String symbol) {
            return (keywords.Contains(symbol));
        }

        private Boolean IsOperator(String s) {
            return (operators.Contains(s));
        }

    }
}
