/*
 *
 * YAQP - Yet Another QSAR Project:
 * Machine Learning algorithms designed for the prediction of toxicological
 * features of chemical compounds become available on the Web. Yaqp is developed
 * under OpenTox (http://opentox.org) which is an FP7-funded EU research project.
 * This project was developed at the Automatic Control Lab in the Chemical Engineering
 * School of the National Technical University of Athens. Please read README for more
 * information.
 *
 * Copyright (C) 2009-2010 Pantelis Sopasakis & Charalampos Chomenides
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact:
 * Pantelis Sopasakis
 * chvng@mail.ntua.gr
 * Address: Iroon Politechniou St. 9, Zografou, Athens Greece
 * tel. +30 210 7723236
 */
package qedit.clients.ontol;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public enum Cause implements java.io.Serializable{

    AlgorithmNotFoundInCache,
    AuthenticationFailed,
    AuthorNotProvided,    
    BibTexNotFoundInDatabase,
    BibTypeNotSupported,
    CommunicationError,
    ConnectionException,
    ContentTypeNotSupported,
    DatabaseError,
    FeatureNotInDataset,
    FileReadingError,
    FileWritingError,
    FilteringError,
    ImproperBibTexResource,
    InputStreamUnreadable,
    InvalidDatasetURI,
    InvalidModelURI,
    InvalidFeatureURI,
    InvalidServiceURI,
    InvalidTaskURI,
    InvalidToken,
    KnoufBibTexClassNotFound,
    KnoufDatatypePropertyNotFound,
    MediaTypeNotSupported,
    NumberExpected,
    OTDatatypePropertyNotFound,
    OutOfRange,
    PolicyCreationError,
    PolicyDeletionError,
    PredictionError,
    PublicationError,
    RegistrationFailed,
    StreamCouldNotClose,
    TrainingError,
    UnauthorizedUser,
    UnknownCauseOfException,
    UpdateFailed,
            
    // CONFIG:
    XA1, XA2, XA3,

    //BATCH PROCESSOR:
    XBP1, XBP2, XBP3, XBP7,
    // PARALLEL PROCESSOR:
    XPP1, XPP2, XPP3, XPP7,

    // PIPELINE
    XPIP1001,                 
    Xtime11735;            
}
