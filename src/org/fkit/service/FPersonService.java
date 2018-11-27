package org.fkit.service;

import org.fkit.entity.one.Person;
import org.fkit.entity.two.FPerson;


public interface FPersonService {
  FPerson loadPerson(Long id);
  void  OneToOne();
  void  ManyToOne();
  void  OneToMany();
  void  ManyToMany();
}