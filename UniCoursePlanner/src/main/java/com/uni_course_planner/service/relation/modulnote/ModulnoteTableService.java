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
			Long lastModulId = -1l;
			if(!tableData.isEmpty())
			{
				lastModulId = tableData.get(tableData.size()-1).getModul_id();
			}
			
			if(lastModulId != modul.getmId().getModulId())
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
