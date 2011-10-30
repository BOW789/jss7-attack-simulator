/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.api;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.mobicents.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.mobicents.protocols.ss7.map.api.primitives.AdditionalNumberType;
import org.mobicents.protocols.ss7.map.api.primitives.AddressNature;
import org.mobicents.protocols.ss7.map.api.primitives.AddressString;
import org.mobicents.protocols.ss7.map.api.primitives.AlertingPattern;
import org.mobicents.protocols.ss7.map.api.primitives.FTNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.IMEI;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.LMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.primitives.MAPPrivateExtension;
import org.mobicents.protocols.ss7.map.api.primitives.NumberingPlan;
import org.mobicents.protocols.ss7.map.api.primitives.USSDString;
import org.mobicents.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.mobicents.protocols.ss7.map.api.service.sms.MWStatus;
import org.mobicents.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.mobicents.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSRequestIndication;
import org.mobicents.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSResponseIndication;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSNotifyRequestIndication;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSNotifyResponseIndication;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSRequestIndication;
import org.mobicents.protocols.ss7.map.api.service.supplementary.UnstructuredSSResponseIndication;
import org.mobicents.protocols.ss7.tcap.asn.comp.GeneralProblemType;
import org.mobicents.protocols.ss7.tcap.asn.comp.InvokeProblemType;
import org.mobicents.protocols.ss7.tcap.asn.comp.Problem;
import org.mobicents.protocols.ss7.tcap.asn.comp.ReturnErrorProblemType;
import org.mobicents.protocols.ss7.tcap.asn.comp.ReturnResultProblemType;

/**
 * 
 * @author amit bhayani
 * @author sergey vetyutnev
 * 
 */
public interface MAPParameterFactory {

	public ProcessUnstructuredSSRequestIndication createProcessUnstructuredSSRequestIndication(byte ussdDataCodingSch, USSDString ussdString,
			AlertingPattern alertingPattern, ISDNAddressString msisdnAddressString);

	public ProcessUnstructuredSSResponseIndication createProcessUnstructuredSSResponseIndication(byte ussdDataCodingScheme, USSDString ussdString);

	public UnstructuredSSRequestIndication createUnstructuredSSRequestIndication(byte ussdDataCodingSch, USSDString ussdString,
			AlertingPattern alertingPattern, ISDNAddressString msisdnAddressString);

	public UnstructuredSSResponseIndication createUnstructuredSSRequestIndication(byte ussdDataCodingScheme, USSDString ussdString);
	
	public UnstructuredSSNotifyRequestIndication createUnstructuredSSNotifyRequestIndication(byte ussdDataCodingSch, USSDString ussdString,
			AlertingPattern alertingPattern, ISDNAddressString msisdnAddressString);

	public UnstructuredSSNotifyResponseIndication createUnstructuredSSNotifyResponseIndication();

	/**
	 * Creates a new instance of {@link USSDString}. The passed USSD String is
	 * encoded by using the default Charset defined in GSM 03.38 Specs
	 * 
	 * @param ussdString
	 *            The USSD String
	 * @return new instance of {@link USSDString}
	 */
	public USSDString createUSSDString(String ussdString);

	/**
	 * Creates a new instance of {@link USSDString} using the passed
	 * {@link java.nio.charset.Charset} for encoding the passed ussdString
	 * String
	 * 
	 * @param ussdString
	 *            The USSD String
	 * @param charSet
	 *            The Charset used for encoding the passed USSD String
	 * @return new instance of {@link USSDString}
	 */
	public USSDString createUSSDString(String ussdString, Charset charSet);

	/**
	 * Creates a new instance of {@link USSDString}. The passed USSD String
	 * byte[] is encoded by using the default Charset defined in GSM 03.38 Specs
	 * 
	 * @param ussdString
	 *            The USSD String
	 * @return new instance of {@link USSDString}
	 */
	public USSDString createUSSDString(byte[] ussdString);

	/**
	 * Creates a new instance of {@link USSDString} using the passed
	 * {@link java.nio.charset.Charset} for encoding the passed ussdString
	 * byte[]
	 * 
	 * @param ussdString
	 *            The byte[] of the USSD String
	 * @param charSet
	 *            The Charset used for encoding the passed USSD String byte[]
	 * @return new instance of {@link USSDString}
	 */
	public USSDString createUSSDString(byte[] ussdString, Charset charSet);

	/**
	 * Creates a new instance of {@link AddressString}
	 * 
	 * @param addNature
	 *            The nature of this AddressString. See {@link AddressNature}.
	 * @param numPlan
	 *            The {@link NumberingPlan} of this AddressString
	 * @param address
	 *            The actual address (number)
	 * @return new instance of {@link AddressString}
	 */
	public AddressString createAddressString(AddressNature addNature, NumberingPlan numPlan, String address);

	public ISDNAddressString createISDNAddressString(AddressNature addNature, NumberingPlan numPlan, String address);

	public FTNAddressString createFTNAddressString(AddressNature addNature, NumberingPlan numPlan, String address);

	/**
	 * Creates a new instance of {@link IMSI}
	 * 
	 * @param data
	 *            whole data string 
	 * @return new instance of {@link IMSI}
	 */
	public IMSI createIMSI(String data);

	public IMEI createIMEI(String imei);

	/**
	 * Creates a new instance of {@link LMSI}
	 * 
	 * @param data
	 * 
	 * @return new instance of {@link LMSI}
	 */
	public LMSI createLMSI(byte[] data);

	/**
	 * Creates a new instance of {@link SM_RP_DA} with imsi parameter
	 * 
	 * @param imsi
	 * @return
	 */
	public SM_RP_DA createSM_RP_DA(IMSI imsi);

	/**
	 * Creates a new instance of {@link SM_RP_DA} with lmsi parameter
	 * 
	 * @param lmsi
	 * @return
	 */
	public SM_RP_DA createSM_RP_DA(LMSI lmsi);

	/**
	 * Creates a new instance of {@link SM_RP_DA} with serviceCentreAddressDA
	 * parameter
	 * 
	 * @param serviceCentreAddressDA
	 * @return
	 */
	public SM_RP_DA createSM_RP_DA(AddressString serviceCentreAddressDA);

	/**
	 * Creates a new instance of {@link SM_RP_DA} with noSM_RP_DA parameter
	 * 
	 * @return
	 */
	public SM_RP_DA createSM_RP_DA();

	/**
	 * Creates a new instance of {@link SM_RP_OA} with msisdn parameter
	 * 
	 * @param msisdn
	 * @return
	 */
	public SM_RP_OA createSM_RP_OA_Msisdn(ISDNAddressString msisdn);

	/**
	 * Creates a new instance of {@link SM_RP_OA} with serviceCentreAddressOA
	 * parameter
	 * 
	 * @param serviceCentreAddressOA
	 * @return
	 */
	public SM_RP_OA createSM_RP_OA_ServiceCentreAddressOA(AddressString serviceCentreAddressOA);

	/**
	 * Creates a new instance of {@link SM_RP_OA} with noSM_RP_OA parameter
	 * 
	 * @return
	 */
	public SM_RP_OA createSM_RP_OA();

	/**
	 * Creates a new instance of {@link MAPUserAbortChoice}
	 * 
	 * @return
	 */
	public MAPUserAbortChoice createMAPUserAbortChoice();

	public MWStatus createMWStatus(boolean scAddressNotIncluded, boolean mnrfSet, boolean mcefSet, boolean mnrgSet);

	public LocationInfoWithLMSI createLocationInfoWithLMSI(ISDNAddressString networkNodeNumber, LMSI lmsi, MAPExtensionContainer extensionContainer,
			AdditionalNumberType additionalNumberType, ISDNAddressString additionalNumber);

	/**
	 * Creates a new instance of {@link MAPPrivateExtension} for
	 * {@link MAPExtensionContainer}
	 * 
	 * @param oId
	 *            PrivateExtension ObjectIdentifier
	 * @param data
	 *            PrivateExtension data (ASN.1 encoded byte array with tag
	 *            bytes)
	 * @return
	 */
	public MAPPrivateExtension createMAPPrivateExtension(long[] oId, byte[] data);

	/**
	 * @param privateExtensionList
	 *            List of PrivateExtensions
	 * @param pcsExtensions
	 *            pcsExtensions value (ASN.1 encoded byte array without tag
	 *            byte)
	 * @return
	 */
	public MAPExtensionContainer createMAPExtensionContainer(ArrayList<MAPPrivateExtension> privateExtensionList, byte[] pcsExtensions);
	
	public Problem createProblemGeneral(GeneralProblemType prob);
	public Problem createProblemInvoke(InvokeProblemType prob);
	public Problem createProblemResult(ReturnResultProblemType prob);
	public Problem createProblemError(ReturnErrorProblemType prob);
}