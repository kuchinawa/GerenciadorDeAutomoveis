package aplicacao;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Huffman {
    public No raiz;

    public void construirArvore(int n, ArrayList<Character> caracteres, ArrayList<Integer> frequencias) {
        PriorityQueue<No> heapMinimo = new PriorityQueue<>(n, (a, b) -> Integer.compare(a.freq, b.freq));

        for (int i = 0; i < n; i++) {
            No no = new No();
            no.caractere = caracteres.get(i);
            no.freq = frequencias.get(i);
            no.esquerda = null;
            no.direita = null;
            heapMinimo.add(no);
        }

        raiz = null;
        while (heapMinimo.size() > 1) {
            No x = heapMinimo.poll();
            No y = heapMinimo.poll();
            No z = new No();
            z.freq = x.freq + y.freq;
            z.caractere = '-';
            z.esquerda = x;
            z.direita = y;
            raiz = z;
            heapMinimo.add(z);
        }
    }


    private class No {
        char caractere;
        int freq;
        No esquerda;
        No direita;
    }

    public ArrayList<Character> contarCaracteresUnicos(String texto) {
        ArrayList<Character> caracteresUnicos = new ArrayList<>();

        for (int i = 0; i < texto.length(); i++) {
            char caractereAtual = texto.charAt(i);

            if (!caracteresUnicos.contains(caractereAtual)) {
                caracteresUnicos.add(caractereAtual);
            }
        }

        return caracteresUnicos;
    }

    public ArrayList<Integer> contarFrequenciaCaracter(String texto, ArrayList<Character> caracteres) {
        ArrayList<Integer> frequencias = new ArrayList<>();

        for (int i = 0; i < caracteres.size(); i++) {
            frequencias.add(0);
        }

        for (int i = 0; i < texto.length(); i++) {
            char caractereAtual = texto.charAt(i);

            if (caracteres.contains(caractereAtual)) {
                int indice = caracteres.indexOf(caractereAtual);
                frequencias.set(indice, frequencias.get(indice) + 1);
            }
        }

        return frequencias;
    }

    public String comprimirTexto(String texto) {
        ArrayList<Character> caracteresUnicos = contarCaracteresUnicos(texto);
        ArrayList<Integer> frequencias = contarFrequenciaCaracter(texto, caracteresUnicos);
        construirArvore(caracteresUnicos.size(), caracteresUnicos, frequencias);

        HashMap<Character, String> mapaCodigos = new HashMap<>();
        construirMapaCodigos(raiz, "", mapaCodigos);

        StringBuilder bitsComprimidos = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            String codigo = mapaCodigos.get(caractere);
            bitsComprimidos.append(codigo);
        }

        return bitsComprimidos.toString();
    }

    private void construirMapaCodigos(No no, String codigo, HashMap<Character, String> mapaCodigos) {
        if (no != null) {
            if (no.esquerda == null && no.direita == null && raiz.caractere == '-') {
                mapaCodigos.put(no.caractere, codigo);
            }
            construirMapaCodigos(no.esquerda, codigo + "0", mapaCodigos);
            construirMapaCodigos(no.direita, codigo + "1", mapaCodigos);
        }
    }

    public String descomprimirTexto(String bitsComprimidos) {
        StringBuilder textoDescomprimido = new StringBuilder();
        No noAtual = raiz;

        for (int i = 0; i < bitsComprimidos.length(); i++) {
            char c = bitsComprimidos.charAt(i);

            if (c == '0') {
                noAtual = noAtual.esquerda;
            } else if (c == '1') {
                noAtual = noAtual.direita;
            }

            assert noAtual != null;
            if (noAtual.esquerda == null && noAtual.direita == null) {
                textoDescomprimido.append(noAtual.caractere);
                noAtual = raiz;
            }
        }

        return textoDescomprimido.toString();
    }
}
