package com.uni_course_planner.service.relation.modulnote;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.dto.modulnote.ModulnoteDTO;
import com.uni_course_planner.relation.modul.modul.Modul;
import com.uni_course_planner.relation.modul.modulnote.Modulnote;
import com.uni_course_planner.repository.modul.*;

@Service
public class ModulnoteTableService 
{
	private ModulRepository modulRep;
	private ModulnoteRepository modulnotenRep;
	
	public ModulnoteTableService(ModulRepository modulRep, ModulnoteRepository modulnotenRep)
	{
		this.modulRep = modulRep;
		this.modulnotenRep = modulnotenRep;
	}
	
	public List<ModulnoteDTO> fillLeistungspunkteTable(Long user)
	{
		List<ModulnoteDTO> tableData = new ArrayList<ModulnoteDTO>();
		
		List<Modul> module = modulRep.findAllByUserId(user);
		
		for(Modul modul : module)
		{
			boolean modulExistsInTable = tableData.stream()
					.anyMatch(m -> m.getModul_id().equals(modul.getmId().getModulId()));
			
			if(modulExistsInTable)
			{
				Optional<Modulnote> modulnote = modulnotenRep.findById(modul.getmId());
				
				ModulnoteDTO dto = new ModulnoteDTO(
					modul.getmId().getModulId(),
					modul.getModulname(),
					modul.getLp(),
					modulnote.isPresent() ? modulnote.get().getNote() : null
				);
				
				tableData.add(dto);
			}		
		}
		
		return tableData;
	}
	
	public int sumByUserId(Long user)
	{
		Integer sum = modulnotenRep.sumByUserId(user);
		return sum == null ? 0 : sum;
	}
}
