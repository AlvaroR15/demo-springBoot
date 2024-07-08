package ar.edu.unju.fi.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.services.IMateriaService;

@Service
public class MateriaServiceImp implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    @Override
    public List<MateriaDTO> findAll() {
        List<Materia> materias = materiaRepository.findAll();
        return materiaMapper.toMateriaDTOs(materias);
    }

    @Override
    public MateriaDTO findById(Long id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        return materia.map(materiaMapper::toMateriaDTO).orElse(null);
    }

    @Override
    public boolean save(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        materiaRepository.save(materia);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }

    @Override
    public void edit(MateriaDTO materiaDTO) throws Exception {
        Optional<Materia> materiaOptional = materiaRepository.findById(materiaDTO.getId());
        if (materiaOptional.isPresent()) {
            Materia materia = materiaMapper.toMateria(materiaDTO);
            materiaRepository.save(materia);
        } else {
            throw new Exception("Materia not found");
        }
    }
}