package com.filipe.resource;

import com.filipe.service.LabSeqService;
import com.filipe.dto.LabSeqResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/labseq")
@Tag(name = "Labseq", description = "Labseq sequence REST API")
@Produces(MediaType.APPLICATION_JSON)
public class LabSeqResource {

    @Inject
    LabSeqService service;

    @GET
    @Path("/{n}")
    @Operation(
            summary = "Get labseq value",
            description = "Returns the value of the LabSeq sequence for the given index n."
    )
    @APIResponse(
            responseCode = "200",
            description = "Labseq value successfully computed",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = LabSeqResponse.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid input (negative index)"
    )
    public LabSeqResponse getLabseq(
            @Parameter(
                    description = "Index of the LabSeq sequence (must be non-negative)",
                    required = true,
                    example = "10"
            )
            @PathParam("n") int n
    ) {
        return new LabSeqResponse(n, service.labseq(n));
    }
}