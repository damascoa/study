/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.repositories;

import com.metre.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Renato
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
