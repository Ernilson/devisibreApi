package br.com.devsibre.Model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formulario")
public class FormularioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_c;
    @Column(length = 200)
    private String nome;
    @Column(length = 20)
    private String fone;
    @Column(length = 40)
    private String email;
    private String data;
    @Column(length = 20)
    private String status;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public FormularioModel() {

    }

    public FormularioModel(Long id_c, String nome, String fone, String email, String data, String status, String cep, String logradouro, String bairro, String localidade, String uf) {
        this.id_c = id_c;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        this.data = data;
        this.status = status;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    /**
     * @return the id_c
     */
    public Long getId_c() {
        return id_c;
    }

    /**
     * @param id_c the id_c to set
     */
    public void setId_c(Long id_c) {
        this.id_c = id_c;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * @param fone the fone to set
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the localidade
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * @param localidade the localidade to set
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id_c);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FormularioModel other = (FormularioModel) obj;
        if (!Objects.equals(this.id_c, other.id_c)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FormularioModel{" + "id_c=" + id_c + ", nome=" + nome + ", fone=" + fone + ", email=" + email + ", data=" + data + ", status=" + status + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + '}';
    }

}
