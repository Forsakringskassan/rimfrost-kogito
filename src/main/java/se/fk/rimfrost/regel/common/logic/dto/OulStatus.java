package se.fk.rimfrost.regel.common.logic.dto;

import org.immutables.value.Value;
import java.util.UUID;

@Value.Immutable
public interface OulStatus
{
   UUID kundbehovsflodeId();

   UUID uppgiftId();

   UppgiftStatus uppgiftStatus();

}
