/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services;

import com.metre.domain.Cidade;
import com.metre.domain.Cliente;
import com.metre.domain.Endereco;
import com.metre.domain.enums.TipoCliente;
import com.metre.dto.ClienteDTO;
import com.metre.dto.ClienteNewDTO;
import com.metre.repositories.CidadeRespository;
import com.metre.repositories.ClienteRepository;
import com.metre.repositories.EnderecoRepository;
import com.metre.services.exceptions.ObjectNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cRepository;
    @Autowired
    private EnderecoRepository eRepository;

    public Cliente buscarPorID(Integer id) {
        Optional<Cliente> cat = cRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado!" + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return cRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return cRepository.findAll(pageRequest);
    }

    public void delete(Integer id) {
        try {
            cRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possivel excluir um cliente que possui pedidos!");
        }
    }

    public Cliente update(Cliente cat) {
        Cliente newobj = buscarPorID(cat.getId());
        updateData(newobj, cat);
        return cRepository.save(newobj);
    }

    public Cliente fromDTO(ClienteDTO obj) {
        return new Cliente(obj.getNome(), obj.getEmail(), null, null);
    }

    @Transactional
    public void inserir(Cliente cat) {
        cRepository.save(cat);
        eRepository.saveAll(cat.getEnderecos());
    }

    private void updateData(Cliente newobj, Cliente obj) {
        newobj.setNome(obj.getNome());
        newobj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO obj) {
        Cliente cli = new Cliente(obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipoCliente()));
//        Cidade cidade = cidRepository.findById(obj.getIdCidade()).orElse(null);
        Cidade cidade = new Cidade();
        cidade.setId(obj.getIdCidade());
        Endereco end = new Endereco(obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cidade);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(obj.getTelefone1());
        if(obj.getTelefone2() != null){
            cli.getTelefones().add(obj.getTelefone2());
        }
        if(obj.getTelefone3() != null){
            cli.getTelefones().add(obj.getTelefone3());
        }
        return cli;
    }
}
