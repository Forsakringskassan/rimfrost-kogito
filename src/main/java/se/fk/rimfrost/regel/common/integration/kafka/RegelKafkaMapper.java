package se.fk.rimfrost.regel.common.integration.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.rimfrost.OperativtUppgiftslagerRequestMessage;
import se.fk.rimfrost.regel.common.*;
import se.fk.rimfrost.regel.common.integration.kafka.dto.OulMessageRequest;
import se.fk.rimfrost.regel.common.integration.kafka.dto.RegelResponse;

@ApplicationScoped
public class RegelKafkaMapper
{

   public RegelResponseMessagePayload toRegelResponseMessagePayload(RegelResponse regelResponse)
   {
      var data = new RegelResponseMessagePayloadData();
      data.setKundbehovsflodeId(regelResponse.kundbehovsflodeId().toString());
      data.setUtfall(regelResponse.utfall());

      var response = new RegelResponseMessagePayload();
      response.setId(regelResponse.id().toString());
      response.setKogitorootprocid(regelResponse.kogitorootprocid());
      response.setKogitorootprociid(regelResponse.kogitorootprociid().toString());
      response.setKogitoparentprociid(regelResponse.kogitoparentprociid().toString());
      response.setKogitoprocid(regelResponse.kogitoprocid());
      response.setKogitoprocinstanceid(regelResponse.kogitoprocinstanceid().toString());
      response.setKogitoprocrefid(regelResponse.kogitoprocinstanceid().toString());
      response.setKogitoprocist(regelResponse.kogitoprocist());
      response.setKogitoprocversion(regelResponse.kogitoprocversion());
      response.setSource(regelResponse.source());
      response.setType(regelResponse.type());
      response.setSpecversion(SpecVersion.NUMBER_1_DOT_0);
      response.setKogitoproctype(KogitoProcType.BPMN);
      response.setData(data);

      return response;
   }

   public OperativtUppgiftslagerRequestMessage toOulRequestMessage(OulMessageRequest messageRequest)
   {
      var request = new OperativtUppgiftslagerRequestMessage();
      request.setVersion("1.0");
      request.setKundbehovsflodeId(messageRequest.kundbehovsflodeId().toString());
      request.setKundbehov(messageRequest.kundbehov());
      request.setRegel(messageRequest.regel());
      request.setRoll(messageRequest.roll());
      request.setBeskrivning(messageRequest.beskrivning());
      request.setVerksamhetslogik(messageRequest.verksamhetslogik());
      request.setUrl(messageRequest.url());
      return request;
   }
}
