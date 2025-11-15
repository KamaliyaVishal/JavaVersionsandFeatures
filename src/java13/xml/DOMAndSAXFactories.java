package java13.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.StringReader;

/**
 * Java 13 DOM and SAX Factories Demo
 * Demonstrates the new factory methods for DOM and SAX parsers: - DocumentBuilderFactory.newDefaultInstance() - SAXParserFactory.newDefaultInstance()
 */
public class DOMAndSAXFactories
{
	public static void main(String[] args)
	{

		System.out.println("=== DOM and SAX Factories Demo ===\n");

		// Sample XML content
		String xmlContent = """
				<?xml version="1.0" encoding="UTF-8"?>
				<root>
				    <person>
				        <name>John Doe</name>
				        <age>30</age>
				        <city>New York</city>
				    </person>
				    <person>
				        <name>Jane Smith</name>
				        <age>25</age>
				        <city>Los Angeles</city>
				    </person>
				</root>
				""";

		// 1. DOM Parser using newDefaultInstance()
		System.out.println("--- DOM Parser (newDefaultInstance) ---");
		try
		{
			// New way (Java 13+)
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = domFactory.newDocumentBuilder();

			// Parse XML from string
			org.w3c.dom.Document document = builder.parse(
					new org.xml.sax.InputSource(new StringReader(xmlContent))
			);

			System.out.println("Document root element: " + document.getDocumentElement().getNodeName());
			System.out.println("Number of person elements: " +
					document.getElementsByTagName("person").getLength());

			// Process elements
			var personNodes = document.getElementsByTagName("person");
			for (int i = 0; i < personNodes.getLength(); i++)
			{
				var person = personNodes.item(i);
				var nameNode = person.getFirstChild();
				while (nameNode != null && !nameNode.getNodeName().equals("name"))
				{
					nameNode = nameNode.getNextSibling();
				}
				if (nameNode != null)
				{
					System.out.println("  Person: " + nameNode.getTextContent());
				}
			}

		}
		catch (Exception e)
		{
			System.err.println("DOM parsing error: " + e.getMessage());
		}

		// 2. SAX Parser using newDefaultInstance()
		System.out.println("\n--- SAX Parser (newDefaultInstance) ---");
		try
		{
			// New way (Java 13+)
			SAXParserFactory saxFactory = SAXParserFactory.newDefaultInstance();
			SAXParser parser = saxFactory.newSAXParser();

			// Custom handler
			DefaultHandler handler = new DefaultHandler()
			{
				private boolean inName = false;
				private boolean inAge = false;

				@Override
				public void startElement(String uri, String localName,
						String qName, org.xml.sax.Attributes attributes)
				{
					if (qName.equals("name"))
					{
						inName = true;
					}
					else if (qName.equals("age"))
					{
						inAge = true;
					}
				}

				@Override
				public void characters(char[] ch, int start, int length)
				{
					if (inName)
					{
						System.out.println("  Name: " + new String(ch, start, length));
						inName = false;
					}
					else if (inAge)
					{
						System.out.println("  Age: " + new String(ch, start, length));
						inAge = false;
					}
				}
			};

			parser.parse(new org.xml.sax.InputSource(new StringReader(xmlContent)), handler);

		}
		catch (SAXException | IOException | ParserConfigurationException e)
		{
			System.err.println("SAX parsing error: " + e.getMessage());
		}

		// 3. Comparison: Old vs New
		System.out.println("\n--- Comparison: Old vs New ---");
		System.out.println("Old way:");
		System.out.println("  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();");
		System.out.println("  SAXParserFactory saxFactory = SAXParserFactory.newInstance();");
		System.out.println();
		System.out.println("New way (Java 13+):");
		System.out.println("  DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();");
		System.out.println("  SAXParserFactory saxFactory = SAXParserFactory.newDefaultInstance();");

		System.out.println("\n--- Benefits ===");
		System.out.println("1. Explicit default instance creation");
		System.out.println("2. Better clarity in code");
		System.out.println("3. Consistent API design");
		System.out.println("4. No functional difference from newInstance()");
		System.out.println("5. Future-proof API");

		System.out.println("\n--- Use Cases ===");
		System.out.println("1. XML document parsing");
		System.out.println("2. XML validation");
		System.out.println("3. XML transformation");
		System.out.println("4. Configuration file parsing");
		System.out.println("5. Data exchange formats");
	}
}

