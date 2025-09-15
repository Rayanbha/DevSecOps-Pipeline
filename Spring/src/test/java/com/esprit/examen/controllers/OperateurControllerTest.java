package com.esprit.examen.controllers;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.OperateurServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OperateurControllerTest {
    @Autowired
    OperateurRepository operateurRepository;

    @Mock
    OperateurRepository operateurRepository2;

    @InjectMocks
    OperateurServiceImpl operateurService;

    Operateur operateur1 = new Operateur(2L,"Yassine","Ben Amor","Yassine",null);

    Long getId()
    {
        for (Operateur ope: operateurRepository.findAll()) {
            return ope.getIdOperateur();
        }
        return 0L;
    }
    @Test
    @Order(0)
    void addOperateur() {
        Operateur operateur = new Operateur();
        List<Operateur> LOperateur = new ArrayList<>();
        for (Long i=1L;i<=10L;i++) {
            operateur.setIdOperateur(i);
            operateur.setPrenom("Yassine");
            operateur.setNom("Ben Amor");
            operateur.setPassword("Yassine");
            Operateur op=operateurRepository.save(operateur);
            LOperateur.add(op);
        }
        assertEquals(10,LOperateur.size());
    }
    @Test
    @Order(3)
    void deleteAll() {
        operateurRepository.deleteAll();
        assertEquals(0,operateurRepository.findAll().spliterator().estimateSize());
    }
    @Test
    @Order(2)
    void retrieveOperateur() {
        Mockito.when(operateurRepository2.findById(Mockito.anyLong())).thenReturn(Optional.of(operateur1));
        Operateur op = operateurService.retrieveOperateur(getId());
        Mockito.verify(operateurRepository2,Mockito.times(1)).findById(getId());
    }
    @Test
    @Order(4)
    void getOperateurs(){
        Iterable<Operateur> LOPerateur = operateurRepository2.findAll();
        Assertions.assertNotNull(LOPerateur);
    }




}
