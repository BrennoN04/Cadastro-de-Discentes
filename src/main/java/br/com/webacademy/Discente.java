package br.com.webacademy;

public record Discente(Long id, String nome, Long matricula, String curso, int periodo_atual) {

    public Discente(String nome, Long matricula, String curso, int periodo_atual) {
        this(null, nome, matricula, curso, periodo_atual);
    }

}
